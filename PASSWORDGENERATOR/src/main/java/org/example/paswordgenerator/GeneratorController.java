package org.example.paswordgenerator;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.security.SecureRandom;

public class GeneratorController {

    @FXML
    private Slider sliderLongitud;

    @FXML
    private RadioButton rbMayusculas;

    @FXML
    private RadioButton rbMinusculas;

    @FXML
    private RadioButton rbNumeros;

    @FXML
    private RadioButton rbSimbolos;

    @FXML
    private TextArea txtResultado;

    @FXML
    private Button btnGenerarContraseña;


    private static final String MAYUSCULAS ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMEROS = "0123456789";
    private static final String SIMBOLOS = "!@#$%^&*()_+-=[]{};:,.<>?";

    private SecureRandom random = new SecureRandom();

    @FXML
    public void initialize() {
        // configuracion del slider (LONGITUD DE LA CONTRASEÑA)
        sliderLongitud.setMin(4);
        sliderLongitud.setMax(20);
        sliderLongitud.setShowTickLabels(true);
        sliderLongitud.setShowTickMarks(true);
        sliderLongitud.setMajorTickUnit(4);
        sliderLongitud.setBlockIncrement(1);
    }


    @FXML
    private void generarContraseña(){
        // obtener la longitud
        int longitud = (int) sliderLongitud.getValue();

        //construir contraseña con los caracteres seleccionados
        StringBuilder caracteresDisponibles = new StringBuilder();

        if(rbMayusculas.isSelected()){
            caracteresDisponibles.append(MAYUSCULAS);
        }
        if (rbMinusculas.isSelected()){
            caracteresDisponibles.append(MINUSCULAS);
        }
        if (rbNumeros.isSelected()){
            caracteresDisponibles.append(NUMEROS);
        }
        if (rbSimbolos.isSelected()){
            caracteresDisponibles.append(SIMBOLOS);
        }

        if (caracteresDisponibles.length() == 0){
            msgAlerta("You must select at least one character type ");
            return;
        }

        // generar contraseña
        StringBuilder contraseña = new StringBuilder();
            for (int i = 0; i < longitud; i++){
                int iAleatorio = random.nextInt(caracteresDisponibles.length());
                contraseña.append(caracteresDisponibles.charAt(iAleatorio));
            }

            // mostrar la contraseña generada
            txtResultado.setText(contraseña.toString());
    }

    // metoodo para lanzar mensaje de error
    private void msgAlerta(String mensaje) {
        ButtonType botonOK = new ButtonType("Ok");
        Alert alert = new Alert(Alert.AlertType.WARNING, mensaje, botonOK);
        alert.setTitle("ERROR");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }



}
