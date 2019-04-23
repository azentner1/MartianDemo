# MartianDemo
- Implemented using MVVM architecture 
- Contains posts, comments for post, albums, photo list, photo, user list, todo list for user
- Local storage using RoomDB
- Use of Kotlin coroutines
- Image loading using Picasso
- Dependency injection using Kodein
- Retrofit for API calls
- Usage of Android Jetpack components

Currently only adding of posts and comments are available. Time constraints prevented implementation of creating new albums/photos and new todos,
but functionality is pretty much the same for those.

- Known issues:
When adding post or comment, a duplicate entry will be added (at the end of the list). This is a bug in an app regarding RoomDb inserting object with id = 0 (will fix, cause it's annoying me)
