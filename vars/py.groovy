import com.devops.scm.git
import com.devops.scripts.training_py

def call(body)
{
    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()

     stage('Invoking  Training Job')
     {
       try {
           def  training_py = new script()
               training_py.pyt()
        }
       catch (Exception error)
             {
               wrap([$class: 'AnsiColorBuildWrapper']) {
               echo "\u001B[41m[ERROR] ${error} execution Traning Job"
               throw error
                                                        }
             }
      }
}

