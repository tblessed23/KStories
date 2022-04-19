package com.example.android.kstories.loggingin;

//public class LoginViewModel extends AndroidViewModel {
//    private AuthAppRegisterRepository authAppRegisterRepository;
//    private MutableLiveData<FirebaseUser> userLiveData;
//
//    public LoginViewModel(@NonNull Application application) {
//        super(application);
//        authAppRegisterRepository = new AuthAppRegisterRepository(application);
//        userLiveData = authAppRegisterRepository.getUserLiveData();
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.P)
//    public void login(String email, String password) {
//        authAppRegisterRepository.login(email, password);
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.P)
//    public void register(String email, String password) {
//        authAppRegisterRepository.register(email, password);
//    }
//
//    public MutableLiveData<FirebaseUser> getUserLiveData() {
//        return userLiveData;
//    }
//}
//
