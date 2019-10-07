import com.devops.scm.git
import com.devops.terraform.tfscripts

def call(body)
{
    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()

     stage('Copying EDA&Data processing scripts')
     {
       try {
           def tf = new tfscripts()
                tf.tfInit()
        }
       catch (Exception error)
             {
               wrap([$class: 'AnsiColorBuildWrapper']) {
               echo "\u001B[41m[ERROR] ${error} copying EDA&DATA"
               throw error
                                                        }
             }
      }
}

