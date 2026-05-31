
const BASE_URL = 'https://projeto-ong-production.up.railway.app';

export const auth = {
  getToken: () => localStorage.getItem('jwt_token'),
  setToken: (t) => localStorage.setItem('jwt_token', t),
  getUser:  () => {
    try {
      return JSON.parse(localStorage.getItem('ong_user'));
    } catch {
      return null;
    }
  },
  setUser:  (u) => localStorage.setItem('ong_user', JSON.stringify(u)),
  logout:   () => {
    localStorage.removeItem('jwt_token');
    localStorage.removeItem('ong_user');
  },
  isLogged: () => !!localStorage.getItem('jwt_token'),
};

async function req(method, path, body = null, requireAuth = true) {
  const headers = { 'Content-Type': 'application/json' };

  if (requireAuth) {
    const token = auth.getToken();
    if (!token) {
      auth.logout();
      window.location.href = 'index.html';
      throw new Error('Não autenticado. Faça login novamente.');
    }
    headers['Authorization'] = `Bearer ${token}`;
  }

  const opts = { method, headers };
  if (body) opts.body = JSON.stringify(body);

  const res = await fetch(`${BASE_URL}${path}`, opts);

  if (res.status === 401) {
    auth.logout();
    window.location.href = 'index.html';
    throw new Error('Sessão expirada. Faça login novamente.');
  }

  if (!res.ok) {
    let msg = `Erro ${res.status}`;
    try {
      const d = await res.json();
      msg = d.message || d.error || msg;
    } catch {}
    throw new Error(msg);
  }

  if (res.status === 204) return null;

  const text = await res.text();
  return text ? JSON.parse(text) : null;
}


export const pessoaAPI = {
  cadastrar: (dados) => req('POST', '/pessoas/cadastro', dados, false),
  login:     (email, senha) => req('POST', '/pessoas/login', { email, senha }, false),
};

export const campanhaAPI = {
  listar:    ()         => req('GET',    '/campanhas'),
  buscarId:  (id)       => req('GET',    `/campanhas/${id}`),
  criar:     (dados)    => req('POST',   '/campanhas', dados),
  atualizar: (id, dados)=> req('PUT',    `/campanhas/${id}`, dados),
  deletar:   (id)       => req('DELETE', `/campanhas/${id}`),
};

export const doacaoAPI = {
  realizar:       (dados)      => req('POST', '/doacoes', dados),
  porDoador:      (doadorId)   => req('GET',  `/doacoes/doador/${doadorId}`),
  porCampanha:    (campanhaId) => req('GET',  `/doacoes/campanha/${campanhaId}`),
};