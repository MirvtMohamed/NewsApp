//        val trial= FirebaseHelper()
//        trial.addNews(NewsM("h","s","f"))
//        var newsList: List<NewsFirebaseModel>
//        lifecycleScope.launch {
//            try {
//                newsList = trial.getNews()
//                // print newslist
//                for (news in newsList) {
//                    Log.d("firebase get corrected",news.title)
//                }
//                // Handle the result here
//            } catch (e: Exception) {
//                // Handle exceptions here
//            }
//        }
//        lifecycleScope.launch {
//            try {
//                trial.deleteNews("GojrOr2wBofJoPOFb6qi")
//                // Handle the result here
//            } catch (e: Exception) {
//                // Handle exceptions here
//            }
//        }