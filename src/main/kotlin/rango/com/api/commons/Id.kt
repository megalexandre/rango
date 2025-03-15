package rango.com.api.commons

import com.fasterxml.uuid.Generators


class Id {

    companion object{
        fun random(): String = Generators.timeBasedGenerator().generate().toString()
    }

}