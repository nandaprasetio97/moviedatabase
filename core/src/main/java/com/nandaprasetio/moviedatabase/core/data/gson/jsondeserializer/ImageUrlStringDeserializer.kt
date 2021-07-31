package com.nandaprasetio.moviedatabase.core.data.gson.jsondeserializer

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.nandaprasetio.moviedatabase.core.domain.entity.urlstring.ImageUrlString
import java.lang.reflect.Type

class ImageUrlStringDeserializer: JsonDeserializer<ImageUrlString> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): ImageUrlString {
        return ImageUrlString(json?.asString)
    }
}