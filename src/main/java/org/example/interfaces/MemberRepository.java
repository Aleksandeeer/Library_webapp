package org.example.interfaces;

import org.example.models.Member;

import java.util.List;

public interface MemberRepository {
    List<Member> getAllMembers();
    void addMember(Member member);
}

