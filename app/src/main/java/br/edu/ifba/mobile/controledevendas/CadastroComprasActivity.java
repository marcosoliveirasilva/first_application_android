package br.edu.ifba.mobile.controledevendas;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.ifba.mobile.controledevendas.bd.Cliente;
import br.edu.ifba.mobile.controledevendas.bd.Compras;
import br.edu.ifba.mobile.controledevendas.fragmentos.FragmentoListaClientes;
import br.edu.ifba.mobile.controledevendas.fragmentos.FragmentoListaProdutos;
import br.edu.ifba.mobile.controledevendas.tarefas.GravacaoCompra;

public class CadastroComprasActivity extends AppCompatActivity {
    private EditText produto = null;
    private EditText preco = null;
    private EditText data = null;

    private Button botaoGravar = null;
    private Compras compras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_compras);

        preparar();
        exibirCompraSelecionada();
    }

    private void preparar() {
        produto = (EditText) findViewById(R.id.produto);
        preco = (EditText) findViewById(R.id.preco);
        data = (EditText) findViewById(R.id.data);

        botaoGravar = (Button) findViewById(R.id.botaoGravarCompra);
        botaoGravar.setOnClickListener(new View.OnClickListener() {// quando clicar n
                                           @Override
                                           public void onClick(View v) {
                                               if (produto.getText().toString().equals("") ||
                                                       preco.getText().toString().equals("") || data.getText().toString().equals("")) {
                                                   Toast.makeText(getContexto(),"Cadastre todos os dados da compra!  ", Toast.LENGTH_LONG).show();
                                               }
                                               else{
                                                   GravacaoCompra gravacao = new GravacaoCompra(getContexto(), getCompras());
                                                   gravacao.execute();
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

    private Compras getCompras()// pegando o que o usuario colocou na tela
    {
        Cliente cliente = FragmentoListaClientes.getInstancia().getClienteSelecionado();

        compras.setCliente("" + cliente.getCodigo());
        compras.setProduto(produto.getText().toString());
        compras.setPreco(preco.getText().toString());
        compras.setData(data.getText().toString());

        return  compras;
    }

    public void exibirCompraSelecionada(){
       compras = FragmentoListaProdutos.getInstancia().getCompraSelecionada();
        if(compras.getCodigo() != -1){
            carregarCampos();
        }
        else
            setCampos();
    }

    private void setCampos(){
        produto.setText("");
        preco.setText("");
        data.setText("");
    }

    private void carregarCampos()
    {
        produto.setText(compras.getProduto());
        preco.setText(compras.getPreco());
        data.setText(compras.getData());

    }
}
