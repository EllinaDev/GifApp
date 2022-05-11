package com.example.giphyiigroup

class Constants {
    companion object {
        const val API_KEY = "iY58eEqqqiI6vKhwV6VBKMUbYxK5t97t"
        const val BASE_URL = "https://api.giphy.com/v1/gifs/"
    }
}

/*----------------------------*//*
        subject.debounce(1000, TimeUnit.MILLISECONDS)
                .switchMap(query -> service.getPostComments(query))
                .subscribe(users -> {
                    System.out.println(users);
                });
        PublishSubject<Integer> subject = PublishSubject.create();
        */