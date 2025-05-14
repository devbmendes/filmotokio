package com.filmotokio.batch.writer;

import com.filmotokio.model.Film;

import java.util.List;

public interface FilmWriteListenerIn {
    void beforeWrite(List<? extends Film> items);

    public void afterWrite(List<? extends Film> items);
    public void onWriteError(Exception exception, List<? extends Film> items);
}
