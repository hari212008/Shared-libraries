import com.devops.terraform

def call(body)
{
    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()

     stage('Starting TF initialization')
     {
       try {
           def init  = new tf()
	       init.tfinit
        }
       catch (Exception error)
             {
               wrap([$class: 'AnsiColorBuildWrapper']) {
               echo "\u001B[41m[ERROR] ${error}  TF initialization"
               throw error
                                                        }
             }
      }
}

