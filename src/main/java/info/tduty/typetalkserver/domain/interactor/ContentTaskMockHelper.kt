package info.tduty.typetalkserver.domain.interactor

class ContentTaskMockHelper {

    companion object {
        fun getFlashcardTaskContent(): String {
            return """
                [
                   {
                        "type": "eng-rus",
                        "front": "test1",
                        "back": "тест1"
                    },
                    {
                        "type": "rus-eng",
                        "front": "тест2",
                        "back": "test2"
                    },
                    {
                        "type": "rus-eng",
                        "front": "тест3",
                        "back": "test3"
                    },
                    {
                        "type": "rus-eng",
                        "front": "тест4",
                        "back": "test4"
                    }
            	]
        """.trimIndent()
        }

        fun getTranslationTaskContent(): String {
            return """
                [
                   {
                        "type": "phrase",
                        "word": "word",
                        "current_translate": "слово"
                    },
                    {
                        "type": "phrase",
                        "word": "teacher's word",
                        "current_translate": "слово учителя"
                    },
                    {
                        "type": "sentence",
                        "word": "It was word",
                        "current_translate": "Это было слово"
                    }
            	]
        """.trimIndent()
        }

        fun getDictionaryPictionaryContent() : String {
            return """
                    [
                        {
                			"picture": "http://192.168.0.105:8080/images/image_task_cloud.jpg",
                	  		"translates": ["cloud", "sky"]
                		},
                		{
                			"picture": "http://192.168.0.105:8080/images/image_task_cloud.jpg",
                	  		"translates": ["cloud", "sky"]
                		},
                		{
                			"picture": "http://192.168.0.105:8080/images/image_task_cloud.jpg",
                	  		"translates": ["cloud", "sky"]
                		}
                	]
            """.trimIndent()
        }
    }
}