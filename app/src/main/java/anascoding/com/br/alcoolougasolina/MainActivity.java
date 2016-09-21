package anascoding.com.br.alcoolougasolina;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtPrecoAlcool = null;
    EditText edtPrecoGasolina = null;
    TextView txtResultado = null;
    Button btnVerificarPrecoButton = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtPrecoAlcool = (EditText) findViewById(R.id.edtPrecoAlcool);
        edtPrecoGasolina = (EditText) findViewById(R.id.edtPrecoGasolina);
        txtResultado = (TextView) findViewById(R.id.txtResultado);
        btnVerificarPrecoButton = (Button) findViewById(R.id.btnVerificarPreco);

        btnVerificarPrecoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String precoAlcoolTexto = edtPrecoAlcool.getText().toString();
                String precoGasolinaTexto = edtPrecoGasolina.getText().toString();

                if (precoAlcoolTexto.isEmpty() || precoGasolinaTexto.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Digite o preço do Álcool e da Gasolina", Toast.LENGTH_SHORT).show();
                } else {
                    Double precoAlcool = Double.parseDouble(precoAlcoolTexto);
                    Double precoGasolina = Double.parseDouble(precoGasolinaTexto);

                    if (precoGasolina <= 0){
                        Toast.makeText(MainActivity.this, "Digite um valor maior que 0 (Zero) para a Gasolina", Toast.LENGTH_SHORT).show();
                    } else {
                        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        inputManager.hideSoftInputFromWindow(getCurrentFocus() == null ? null : getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                        Double resultado = precoAlcool / precoGasolina;
                        if (resultado >= 0.7) {
                            txtResultado.setText(R.string.resultado_gasolina);
                        } else {
                            txtResultado.setText(R.string.resultado_alcool);
                        }
                    }
                }
            }
        });
    }
}