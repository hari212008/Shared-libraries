import com.devops.terraform.terraform

def call(body)
{
    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()

     stage('Cloning the code')
     {
       try {
           def tf = new terraform()
	   BRANCH_NAME = 'develop'
         // ## println "branch name is ${BRANCH_NAME} "
	   tf.setValue("${config.BRANCH_NAME}","${config.SCM_URL}")
           tf.codeCheckout()	   
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

