package com.example.cp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Usuario (
    @PrimaryKey val email:String,@ColumnInfo (name="Senha")val senha:String,@ColumnInfo (name="HabilitadoParaLogin") val HabilitadoParaFazerLogin:Boolean
)