import com.devops.terraform.tfscripts
import com.devops.scm.git

def call(body)
{
    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()

    stage('Terraform Initilization')
    {
      try {
            def execute = new tfscripts()
	    execute.setValue("${config.action}")	            
	    execute.terraform()
		          
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
