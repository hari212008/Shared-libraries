import com.devops.scm.git
import com.devops.scripts.script

def call(body)
{
    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()

     stage('Invoking  Training Job')
     {
       try {
           def scripts = new script()
               scripts.python()
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

