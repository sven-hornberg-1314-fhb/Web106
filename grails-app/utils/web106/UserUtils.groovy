package web106

import org.springframework.security.core.context.SecurityContextHolder

/**
 * Created with IntelliJ IDEA.
 * User: marcman
 * Date: 19.11.13
 * Time: 14:53
 * To change this template use File | Settings | File Templates.
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
