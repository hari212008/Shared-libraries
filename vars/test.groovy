def call(body)
{
    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()

    stage('Preparing CI Environment')
    {
      try {
            def execute = new tfscripts()
            execute.terraforminit()
          }
      catch (Exception error)
            {
              wrap([$class: 'AnsiColorBuildWrapper']) {
             echo "\u001B[41m[ERROR] ${error}"
              throw error
                                                      }
            }

    }

