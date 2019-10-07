import com.devops.scm.terraform
import com.devops.scm.git
import com.devops.datacopy.data

def call(body)
{
    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()

     stage('Cloning the code')
     {
       try {
           def terraform = new tf()
         // ## println "branch name is ${BRANCH_NAME} "
           scm.setValue("${config.BRANCH_NAME}")
           scm.codeCheckout()
	   }
       catch (Exception error)
             {
               wrap([$class: 'AnsiColorBuildWrapper']) {
               echo "\u001B[41m[ERROR] ${error}"
               throw error
                                                        }
             }
      }
}

