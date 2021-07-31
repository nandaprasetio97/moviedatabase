package com.nandaprasetio.moviedatabase.core.data.gson.jsondeserializer

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.nandaprasetio.moviedatabase.core.domain.entity.urlstring.AvatarUrlString
import java.lang.reflect.Type

class AvatarUrlStringDeserializer: JsonDeserializer<AvatarUrlString> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): AvatarUrlString {
        return AvatarUrlString(json?.asString)
    }
}