package br.ufjf.dcc196.pedrocottabadaro.calculapreco;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private EditText editTextPrecoProduto;
    private TextView textViewPrecoFinal;
    private CheckBox checkBoxExpresso;
    private CheckBox checkBoxPresente;
    private RadioGroup radioGroupPagamento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextPrecoProduto=findViewById(R.id.editTextPrecoProduto);
        textViewPrecoFinal=findViewById(R.id.textViewPrecoFinal);
        checkBoxExpresso=findViewById(R.id.checkBoxExpresso);
        checkBoxPresente=findViewById(R.id.checkBoxPresente);
        radioGroupPagamento=findViewById(R.id.radioGroupPagamento);

        CompoundButton.OnCheckedChangeListener ouvinteCheckbox=new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                calcular(buttonView);
            }
        };

        checkBoxExpresso.setOnCheckedChangeListener(ouvinteCheckbox);
        checkBoxPresente.setOnCheckedChangeListener(ouvinteCheckbox);

        radioGroupPagamento.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                calcular(group);
            }
        });


        editTextPrecoProduto.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                calcular(v);
                return false;
            }
        });
    }

    public void calcular(View view){
        Double precoProduto=0.0;
        Double precoFinal=0.0;

        try {
           precoProduto=Double.parseDouble(editTextPrecoProduto.getText().toString());
           precoFinal=precoProduto+10.0;

        }
        catch (Exception e){

        }
        Locale locale= new Locale( "pt", "BR");
        if(checkBoxExpresso.isChecked()){
            precoFinal+=10.0;
        }
        if(checkBoxPresente.isChecked()){
            precoFinal+=5.0;
        }

        switch(radioGroupPagamento.getCheckedRadioButtonId()){

            case R.id.radioButtonCartao1x:
                precoFinal+=0.03*precoProduto;
                break;
            case R.id.radioButtonCartao2x:
                precoFinal+=0.08*precoProduto;
                break;
            case R.id.radioButtonCartao3x:
                precoFinal+=0.13*precoProduto;
                break;

        }
        textViewPrecoFinal.setText(NumberFormat.getCurrencyInstance(locale).format(precoFinal));

    }
}
