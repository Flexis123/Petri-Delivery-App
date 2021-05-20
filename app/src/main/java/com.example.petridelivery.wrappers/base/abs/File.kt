package com.example.petridelivery.wrappers.base.abs

class File(private var fileName: String) {

    fun setFileName(fileName: String) {
        require(!fileName.contains("/")) { "file cannot have forward slash" }
        this.fileName = fileName
    }

    override fun toString(): String {
        return fileName
    }

    init {
        setFileName(fileName)
    }
}