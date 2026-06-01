function toggleProfile() {
  const toggle = document.getElementById('profile-toggle');
  const btn = document.getElementById('btn-login');
  const link = document.getElementById('link-cadastro');

  if (!toggle || !btn || !link) return;

  if (toggle.checked) {
    btn.innerText = 'Entrar como Organizador';
    link.innerText = 'Cadastre sua ONG';
    link.href = 'cadastro_pessoa.html?perfil=organizador';
  } else {
    btn.innerText = 'Entrar como Doador';
    link.innerText = 'Cadastre-se como Doador';
    link.href = 'cadastro_pessoa.html?perfil=doador';
  }
}
