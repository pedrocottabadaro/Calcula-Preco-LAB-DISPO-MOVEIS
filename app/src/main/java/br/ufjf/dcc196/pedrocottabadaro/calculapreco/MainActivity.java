package br.ufjf.dcc196.pedrocottabadaro.calculapreco;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private EditText editTextPrecoProduto;
    private TextView textViewPrecoFinal;
    private CheckBox checkBoxExpresso;
    private CheckBox checkBoxPresente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextPrecoProduto=findViewById(R.id.editTextPrecoProduto);
        textViewPrecoFinal=findViewById(R.id.textViewPrecoFinal);
        checkBoxExpresso=findViewById(R.id.checkBoxExpresso);
        checkBoxPresente=findViewById(R.id.checkBoxPresente);

    }

    public void calcular(View view){
        Double precoProduto=Double.parseDouble(editTextPrecoProduto.getText().toString());
        Locale locale= new Locale( "pt", "BR");
        Double precoFinal=precoProduto+10.0;
        if(checkBoxExpresso.isChecked()){
            precoFinal+=10.0;
        }
        if(checkBoxPresente.isChecked()){
            precoFinal+=5.0;
        }
        textViewPrecoFinal.setText(NumberFormat.getCurrencyInstance(locale).format(precoFinal));

    }
}
