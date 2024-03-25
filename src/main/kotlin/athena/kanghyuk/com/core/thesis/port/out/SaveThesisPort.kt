package athena.kanghyuk.com.core.thesis.port.out

import athena.kanghyuk.com.application.thesis.entity.Thesis

interface SaveThesisPort {

    fun save(entity: Thesis): Thesis
}
