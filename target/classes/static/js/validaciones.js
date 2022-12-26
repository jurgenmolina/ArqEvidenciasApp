/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function validarDatos(){
    
    var password1 = frmUsuario.password.value;
    var password2 = frmUsuario.repetir_password.value;
    
    if (password1!=password2) {
        document.getElementById("divMensaje").innerHTML = "Error los passwords no coinciden";
        frmUsuario.repetir_password.focus();
        return false;
    }
    
}


