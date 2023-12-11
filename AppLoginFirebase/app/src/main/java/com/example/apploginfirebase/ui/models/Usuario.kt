package com.example.apploginfirebase.ui.models

import java.io.Serializable

class Usuario : Serializable {
    var id: String? = null
    var image_profile: ByteArray? = null
    var nome: String? = null
    var email: String? = null

    constructor() {}
    constructor(id: String?, nome: String?, email: String?) {
        this.id = id
        this.nome = nome
        this.email = email
    }
}