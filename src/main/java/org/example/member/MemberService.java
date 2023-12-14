package org.example.member;

import org.example.global.Container;

public class MemberService {
    MemberRepository memberRepository;

    MemberService () {
        memberRepository = new MemberRepository();
    }

    public void join (String userId, String password) {
        this.memberRepository.join(userId, password);
    }
    public void exit (String userId) {
        this.memberRepository.exit(userId);
    }
    public Member memberFindByUserId(String userId) {
        return this.memberRepository.memberFindByUserId(userId);
    }

    public void login(Member checkedMember) {
        Container.setLoginedMember(checkedMember);
    }

    public void logout() {
        Container.setLoginedMember(null);
    }
}
