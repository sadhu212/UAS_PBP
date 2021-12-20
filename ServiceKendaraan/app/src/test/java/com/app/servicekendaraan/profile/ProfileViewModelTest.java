package com.app.servicekendaraan.profile;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.app.Application;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

@RunWith(JUnit4.class)
public class ProfileViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    private ProfileViewModel profileViewModel;

    @Mock
    Observer<String> nameObserver;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        profileViewModel =  new ProfileViewModel(Mockito.mock(Application.class));
        profileViewModel.name.observeForever(nameObserver);
    }

    @Test
    private void nameMustNotNull() {
        String name = null;
        profileViewModel.name.setValue(name);

        verify(nameObserver).onChanged("name not null");
    }

}