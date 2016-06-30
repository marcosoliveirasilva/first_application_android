package br.edu.ifba.mobile.controledevendas;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.ifba.mobile.controledevendas.bd.Cliente;
import br.edu.ifba.mobile.controledevendas.fragmentos.FragmentoListaClientes;
import br.edu.ifba.mobile.controledevendas.tarefas.GravacaoCliente;

public class CadastroClientesActivity extends AppCompatActivity {

    private EditText nome = null;
    private EditText email = null;
    private EditText telefone = null;

    private Button botaoGravar = null;
    private Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_clientes);

        preparar();
        exibirClienteSelecionada();
    }



    private void preparar() {
        nome = (EditText) findViewById(R.id.nome);
        email = (EditText) findViewById(R.id.email);
        telefone = (EditText) findViewById(R.id.telefone);

        botaoGravar = (Button) findViewById(R.id.botaoGravar);
        botaoGravar.setOnClickListener(new View.OnClickListener() {// quando clicar n
                                           @Override
                                           public void onClick(View v) {
                                                if (nome.getText().toString().equals("") || email.getText().toString().equals("")
                                                        || telefone.getText().toString().equals("")) {
                                                    Toast.makeText(getContexto(),"Cadastre todos os dados do cliente!  ", Toast.LENGTH_LONG).show();
                                                }
                                               else{
                                                    GravacaoCliente gravacao = new GravacaoCliente(getContexto(), getCliente());
                                                    gravacao.execute();
                                                    cliente = FragmentoListaClientes.getInstancia().getClienteSelecionado();

                                                    if(cliente.getCodigo() == -1)
                                                        setCampos();
                                                }

                                           }
                                       }
        );
    }

    private Context getContexto()
    {
        return this;
    }

    private Cliente getCliente()// pegando o que o usuario colocou na tela
    {
        cliente.setNome(nome.getText().toString());
        cliente.setEmail(email.getText().toString());
        cliente.setTelefone(telefone.getText().toString());


        return cliente;
    }

    public void exibirClienteSelecionada(){
        cliente = FragmentoListaClientes.getInstancia().getClienteSelecionado();

        if(cliente.getCodigo() != -1)
            carregarCampos();
        else
            setCampos();

    }

    private void setCampos(){
        nome.setText("");
        email.setText("");
        telefone.setText("");

    }

    private void carregarCampos()
    {
        nome.setText(cliente.getNome());
        email.setText(cliente.getEmail());
        telefone.setText(cliente.getTelefone());
    }
}
