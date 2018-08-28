package a.agendamento.agendacontato.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import a.agendamento.agendacontato.modelo.Contato;

public class ContatoDAO extends SQLiteOpenHelper {
    private static final int VERSAO = 1;
    private static final String TABELA = "Contatos";
    private static final String DATABASE = "CadastroContato";

    public ContatoDAO(Context context){
        super(context, DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String ddl = "CREATE TABLE " + TABELA
                + " (id INTEGER PRIMARY KEY, "
                + " nome TEXT NOT NULL, "
                + " telefone TEXT, "
                + " endereco TEXT);";
        db.execSQL(ddl);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABELA;
        db.execSQL(sql);
        onCreate(db);
    }

    public void insere(Contato contato){
        ContentValues values = new ContentValues();

        values.put("nome", contato.getNome());
        values.put("telefone", contato.getTelefone());
        values.put("endereco", contato.getEndereco());

        getWritableDatabase().insert(TABELA, null, values);
    }

    public List<Contato> getLista(){
        List<Contato> contatos = new ArrayList<Contato>();

        SQLiteDatabase db = getWritableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + TABELA + ";", null);

        while (c.moveToNext()){
            Contato contato = new Contato();
            contato.setId(c.getLong(c.getColumnIndex("id")));
            contato.setNome(c.getString(c.getColumnIndex("nome")));
            contato.setTelefone(c.getString(c.getColumnIndex("telefone")));
            contato.setEndereco(c.getString(c.getColumnIndex("endereco")));
            contatos.add(contato);
        }
        c.close();

        return contatos;
    }

    public void deletar(Contato contato){
        String[] args = {contato.getId().toString()};
        getWritableDatabase().delete(TABELA, "id=?", args);
    }
}
