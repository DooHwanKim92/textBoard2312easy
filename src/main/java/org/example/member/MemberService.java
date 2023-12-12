package org.example.member;

public class MemberService {
    MemberRepository memberRepository;
    MemberService() {
        memberRepository = new MemberRepository();
    }
    public void joinMembership() {
        memberRepository.joinMembership();
    }
    public void logIn() {
        memberRepository.logIn();
    }
    public void logOut() {
        memberRepository.logOut();
    }
    public boolean checkId(String userId) {
        return memberRepository.checkId(userId);
    }
    public boolean checkPassword(String password, String checkPassword) {
        return memberRepository.checkPassword(password,checkPassword);
    }
}
