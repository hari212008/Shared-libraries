import com.devops.terraform.tfscripts

def call(body)
{
    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()

     stage('Starting TF initialization')
     {
       try {
           def provision  = new tfscripts()
	       provision.tfInit()
             {
               wrap([$class: 'AnsiColorBuildWrapper']) {
               echo "\u001B[41m[ERROR] ${error}  TF initialization"
               throw error
                                                        }
             }
      }
}
}

