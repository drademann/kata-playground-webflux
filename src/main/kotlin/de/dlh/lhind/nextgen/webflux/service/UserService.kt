package de.dlh.lhind.nextgen.webflux.service

import de.dlh.lhind.nextgen.webflux.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(val userRepository: UserRepository) {

    fun all() = userRepository.all()

}