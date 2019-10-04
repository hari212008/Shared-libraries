import com.devops.scm.git

def call(body)
{
    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()

     stage('Cloning the code')
     {
       try {
           def scm = new git()
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
