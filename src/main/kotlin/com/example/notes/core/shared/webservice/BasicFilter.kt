package com.example.notes.core.shared.webservice

data class BasicFilter (val name : String, val value : String){
    override fun toString(): String = "BasicFilter=($name,$value)"
}