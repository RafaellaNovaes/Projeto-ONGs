function toggleProfile() {
    const toggle = document.getElementById('profile-toggle');
    const btn = document.getElementById('btn-login');
    const link = document.getElementById('link-cadastro');
    const doadorArea = document.getElementById('doador-area');
    const organizadorArea = document.getElementById('organizador-area');

    if (toggle && btn && link) {
        if (toggle.checked) {
            btn.innerText = "Entrar como Organizador";
            link.innerText = "Cadastre sua ONG";
            link.href = "cadastro_pessoa.jsp?perfil=organizador";

            if(doadorArea) doadorArea.style.display = 'none';
            if(organizadorArea) organizadorArea.style.display = 'block';
        } else {
            btn.innerText = "Entrar como Doador";
            link.innerText = "Cadastre-se como Doador";
            link.href = "cadastro_pessoa.jsp?perfil=doador";

            if(doadorArea) doadorArea.style.display = 'block';
            if(organizadorArea) organizadorArea.style.display = 'none';
        }
    }
}

document.addEventListener("DOMContentLoaded", function() {
    const form = document.querySelector("form");

    if (form) {
        form.onsubmit = function(event) {
            const campoIni = document.getElementsByName("txtdataini")[0];
            const campoFim = document.getElementsByName("txtdatafim")[0];

            if (campoIni && campoFim && campoIni.value && campoFim.value) {
                const dataIni = new Date(campoIni.value);
                const dataFim = new Date(campoFim.value);

                if (dataFim < dataIni) {
                    alert("⚠️ Erro: A data de término não pode ser anterior à data de início.");
                    event.preventDefault();
                    return false;
                }
            }
            console.log("🚀 Dados validados! Processando envio...");
        };
    }
});