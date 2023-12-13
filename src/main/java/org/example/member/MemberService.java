package org.example.member;

public class MemberService {
    MemberRepository memberRepository;
    MemberService() {
        memberRepository = new MemberRepository();
    }
    public void joinMembership(String userId, String password) {
        memberRepository.joinMembership(userId,password);
    }
    public Member logIn(String userId, String password) {
        return memberRepository.logIn(userId, password);
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
