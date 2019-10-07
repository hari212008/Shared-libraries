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
           def terraform = new tf()
         // ## println "branch name is ${BRANCH_NAME} "
           terraform.setValue("${config.BRANCH_NAME}")
           terraform.codeCheckout()
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

