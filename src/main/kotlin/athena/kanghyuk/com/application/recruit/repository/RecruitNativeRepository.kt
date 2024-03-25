package athena.kanghyuk.com.application.recruit.repository

import athena.kanghyuk.com.application.TableNames
import athena.kanghyuk.com.application.recruit.entity.Recruit
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.Repository
import org.springframework.data.repository.query.Param
import java.time.LocalDate

@org.springframework.stereotype.Repository
interface RecruitNativeRepository: Repository<Recruit, Long?> {

    @Query(
        value = "SELECT * FROM ${TableNames.RECRUIT} r " +
                "where r.start_at <= :date AND :date <= r.end_at LIMIT 1",
        nativeQuery = true
    )
    fun findByDateBetween(@Param("date") date: LocalDate): Recruit
}