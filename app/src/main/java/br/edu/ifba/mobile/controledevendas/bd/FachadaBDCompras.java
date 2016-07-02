package br.edu.ifba.mobile.controledevendas.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class FachadaBDCompras extends SQLiteOpenHelper {
	private static FachadaBDCompras instancia = null;

	public static FachadaBDCompras criarInstancia(Context context)
	{
		if(instancia == null)
		{
			instancia = new FachadaBDCompras(context);
		}
		return instancia;
	}

	public static FachadaBDCompras getInstancia()
	{
		return instancia;
	}

	private static String NOME_BANCO = "ControleCompras";
	private static int VERSAO_BANCO = 1;

	public FachadaBDCompras(Context context) {
		super(context,NOME_BANCO, null, VERSAO_BANCO);

	}

	private static String COMANDO_CRIACAO_TABELA_COMPRAS =
			"CREATE TABLE COMPRAS(" +
					"CODIGO INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ "NOME TEXT, PRODUTO TEXT, PRECO REAL, DATA TEXT)";

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(COMANDO_CRIACAO_TABELA_COMPRAS);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int versaoNova) {
		// TODO Auto-generated method stub
	}

	// metodos de criacao de um CRUD utilizando o SQLite
	
	public long inserir(Compras compras) {

		SQLiteDatabase db = this.getWritableDatabase(); //this chama a prorpia classe
		ContentValues valores= new ContentValues(); // objeto que vai pegar as notas das disciplinas
		valores.put("NOME", compras.getCliente());
		valores.put("PRODUTO", compras.getProduto());
		valores.put("PRECO", compras.getPreco());
		valores.put("DATA", compras.getData());

		long codigo = db.insert ("COMPRAS",null,valores); //inserindo as disciplinas no banco de dadaos/ codigo vai receber um id
		return codigo;
	}

	public long atualizar(Compras compras) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues valores= new ContentValues(); // objeto que vai pegar as notas das disciplinas
		valores.put("NOME", compras.getCliente());
		valores.put("PRODUTO", compras.getProduto());
		valores.put("PRECO", compras.getPreco());
		valores.put("DATA", compras.getData());
		long codigo = db.update("COMPRAS", valores,"CODIGO ="+ compras.getCodigo(), null); //atualizando a tabela onde o codigo é esse que estamos passando/ update também retorna m sodigo
		return codigo;

	}



	public int remover(Compras compras) {
		SQLiteDatabase db= this.getWritableDatabase();

		return db.delete("COMPRAS", "CODIGO="+compras.getCodigo(),null);//deletando as diciplinas onde o codigo é ge.codigo
	}

	public void removerTodos( String compras) {
		SQLiteDatabase db = this.getWritableDatabase();

		db.delete("COMPRAS", "NOME="+compras,null);
	}

	public List<Compras> listarCompras(String cliente) {
		List<Compras> listaCompras = new ArrayList<Compras>();// criando lista de disciplina

		SQLiteDatabase db = this.getReadableDatabase();

		String selecao = " SELECT CODIGO, NOME, PRODUTO, PRECO, DATA FROM COMPRAS";
		Cursor cursor = db.rawQuery(selecao, null); //executando esse comando selecao que mandamos / query retorna um recurso que é um cursor que  é um apontador para cada linha de registro.nesse caso é as proprias linhas da tabela
		if(cursor!=null)
		{
			boolean temProximo = cursor.moveToFirst();// indo para a primeira linha do registro da tabela

			while (temProximo)
			{
				Compras compras = new Compras();
				compras.setCodigo(cursor.getLong(cursor.getColumnIndex("CODIGO" )));// setando os campos na tabela disciplina
				compras.setCliente(cursor.getString(cursor.getColumnIndex("NOME")));
				compras.setProduto(cursor.getString(cursor.getColumnIndex("PRODUTO")));
				compras.setPreco(cursor.getDouble(cursor.getColumnIndex("PRECO")));
				compras.setData(cursor.getString(cursor.getColumnIndex("DATA")));


				int indice = cursor.getColumnIndex("NOME");
				String nome = cursor.getString(indice);


				if (nome.equals(cliente)){
					listaCompras.add(compras);
				}

				temProximo = cursor.moveToNext();// percorrendo toda a linha
			}
		}
		return  listaCompras;

	}



	
}