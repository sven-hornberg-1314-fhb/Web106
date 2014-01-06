package web106

import org.springframework.security.core.context.SecurityContextHolder

/**
 *
 */
class UserUtils {

    def getEmailFromCurrentUser() {
        def authorities = SecurityContextHolder.getContext().authentication.authorities
        def email = null
        authorities.each(){
            if(it.toString().startsWith('email')){
                 email = it.toString().substring(6)
            }
        }
        return email
    }
}
