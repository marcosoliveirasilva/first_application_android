package br.edu.ifba.mobile.controledevendas.tarefas;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import br.edu.ifba.mobile.controledevendas.bd.Cliente;
import br.edu.ifba.mobile.controledevendas.bd.FachadaBD;
import br.edu.ifba.mobile.controledevendas.bd.FachadaBDCompras;


/**
 * Created by alunoifba on 27/05/2016.
 */
public class RemocaoCliente extends  AsyncTask<Void, Void, String> {

    private  Context contexto = null;
    private Cliente cliente = null;

    public RemocaoCliente(Context contexto, Cliente cliente)
    {
        this.contexto = contexto;
        this.cliente = cliente;

    }

    @Override
    protected String doInBackground(Void... params) { // vai executar em segundo plano. O banco de dados [e executado em segundo plano
       String mensagem = "";

        if(cliente.getCodigo() != -1)  {
             if(FachadaBD.getInstancia().remover(cliente) == 0){
                 mensagem = " Problemas de remocao";
             } else {
                 mensagem = "Cliente removido";
                 FachadaBDCompras.getInstancia().removerTodos(""+cliente.getCodigo());
             }
        }
        else
        {
            mensagem = "Selecione um cliente";
        }

        return mensagem;
    }

    @Override
    protected void onPostExecute(String mensagem)//depois da execucao// somente aqui que deve exibir a mensagem
    {
        Toast.makeText(contexto, mensagem, Toast.LENGTH_LONG).show();//toast serve para  notificar. Sao as mensagens do android
    }
}
