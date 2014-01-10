package web106

import grails.transaction.Transactional
import org.springframework.beans.factory.InitializingBean

@Transactional
class BootStrapWeb106Service {

    void init() {

        print 'hello from BootStrapWeb106Service init '
    }
}
