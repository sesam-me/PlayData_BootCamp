package controller;

import service.MovieService;

public class MovieController {
    private static MovieController controller;

    public static MovieController getController() {
        if(controller == null) controller = new MovieController();
        return controller;
    }

    public void watchMovie() {
        System.out.println(MovieService.getService().shownMovies());
    }
}
