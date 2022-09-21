// Client code for Atelier Serverless sample (2022-09).
// The Client (browser) talks only to the Cloud Run backend, never directly to
// the Etablissements API.

let fetchListButton = document.getElementById('fetch-list');
let errorBanner = document.getElementById('errors');
let listeNomsEtablissements = document.getElementById('noms-etablissements');
let detail = document.getElementById('detail');

let etabLibelle = document.getElementById('etab-libelle');
let etabCode = document.getElementById('etab-code');
let etabCodeSociete = document.getElementById('etab-code-societe');
let etabSiret = document.getElementById('etab-siret');
let etabTelephone = document.getElementById('etab-telephone');

function logError(msg) {
    errorBanner.innerText = msg;
    errorBanner.classList.remove('hidden');
}

function clearError() {
    errorBanner.classList.add('hidden');
    errorBanner.innerText = '';
}

fetchListButton.addEventListener( 'click', function () {
    // Fetch the full list of (code, libelle) of Etablissement
    clearError();
    let u = "/names";
    fetch(u)
        .then((response) => {
            if (!response.ok) {
                return Promise.reject(response);
            }
            return response.json()
        })
        .then((data) => {
            //console.log(data);
            removeOptions(listeNomsEtablissements);
            data.forEach((obj) => {
                listeNomsEtablissements.options[listeNomsEtablissements.options.length] = new Option(obj.libelle, obj.code);
            });
            listeNomsEtablissements.classList.remove('hidden');
        })
        .catch(async (error) => {
            logError('Could not retrieve the list: ' + error.status + '\n' + await error.text());
        });
}, false);

listeNomsEtablissements.addEventListener( 'change', function () {
    clearError();
    clearDetail();

    if(!this.value) {
        detail.classList.add('hidden');
        return;
    }

    // Fetch the detail of 1 Etablissement, by its code
    let u = "/detail/" + this.value;
    fetch(u)
        .then((response) => {
            if (!response.ok) {
                return Promise.reject(response);
            }
            return response.json()
        })
        .then((data) => {
            etabLibelle.innerText = data.libelle;
            etabCode.innerText = data.code;
            etabCodeSociete.innerText = data.codeSociete;
            etabSiret.innerText = data.siret;
            etabTelephone.innerText = data.telephone || "-";
            detail.classList.remove('hidden');
        })
        .catch(async (error) => {
            logError(`Could not retrieve the detail for ${this.value} : ` + error.status + '\n' + await error.text());
        });
}, false );

function removeOptions(selectElement) {
    // From https://stackoverflow.com/a/3364546/871134
    var i, L = selectElement.options.length - 1;
    for(i = L; i >= 0; i--) {
       selectElement.remove(i);
    }
 }

function clearDetail() {
    detail.classList.add('hidden');
    etabLibelle.innerHTML = '';
    etabCode.innerHTML = '';
    etabCodeSociete.innerHTML = '';
    etabSiret.innerHTML = '';
    etabTelephone.innerHTML = '';
}
     