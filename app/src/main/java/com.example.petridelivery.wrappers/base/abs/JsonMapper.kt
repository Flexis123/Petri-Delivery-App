package com.example.petridelivery.wrappers.base.abs

interface JsonMapper {
    fun <T> fromJson(json: String?, cls: Class<T>): T?
    fun toJson(obj: Any?): String
    fun <T> fromJson(f: File, cls: Class<T>): T?
    fun toJson(f: File, obj: Any?)
}