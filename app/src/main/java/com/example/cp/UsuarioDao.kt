package com.example.cp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UsuarioDao {
    @Query("SELECT * FROM Usuario")
    fun recuperarTodos():List<Usuario>

    @Query("SELECT * FROM Usuario WHERE email IN (:emailUsuarios)")
    fun carregarTodosPorEmail(emailUsuarios: Array<String>): List<Usuario>

    @Query("SELECT * FROM Usuario WHERE email IN (:emailUsuarios)")
    fun checarExistenciaDeUsuario(emailUsuarios: String): List<Usuario>
    @Query("UPDATE Usuario set email=:emailNovo,Senha=:senha,HabilitadoParaLogin=:habilitadoAFazerLogin Where email=:emailAntigo")
    fun alterarUsuario(emailAntigo:String,emailNovo:String,senha:String,habilitadoAFazerLogin:Boolean)

    @Query("SELECT * FROM Usuario WHERE email LIKE :Email  LIMIT 1")
    fun encontrarEmail(Email: String): Usuario
    @Insert
    fun inserirTodos(users: List<Usuario>)
    @Delete
    fun deletar(user: Usuario)
    @Query("delete from usuario")
    fun deletarTodos()

}