package athena.kanghyuk.com.core.thesis.port.out

import athena.kanghyuk.com.application.recruit.entity.Recruit
import athena.kanghyuk.com.application.thesis.entity.Thesis

interface ReadThesisPort {

    fun readAllByRecruit(recruit: Recruit): List<Thesis>
}
