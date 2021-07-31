package com.nandaprasetio.moviedatabase.core.domain.entity.people

import com.nandaprasetio.moviedatabase.core.domain.entity.urlstring.ImageUrlString

abstract class BasePeople {
    abstract val id: Int
    abstract val adult: Boolean
    abstract val gender: Int
    abstract val knownForDepartment: String
    abstract val name: String
    abstract val originalName: String
    abstract val popularity: Float
    abstract val profilePath: ImageUrlString?
}