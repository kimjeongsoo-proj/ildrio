package ilike.ildrio.config.security;

import ilike.ildrio.model.member.MemberInfoModel;
import ilike.ildrio.service.member.MemberInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class WebUserDetailsService implements UserDetailsService {

    @Autowired
    private MemberInfoService memberInfoService;

    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        MemberInfoModel request = new MemberInfoModel();
        request.setMemberId(memberId);
        MemberInfoModel member = memberInfoService.getMap_memberInfo(request);
        
        System.out.println("User Loaded - MemberId: " + memberId + ", Authorities:>>>>>> " + member.getMemberPassword());
        
        if (member == null || member.getMemberId() == null) {
            throw new UsernameNotFoundException("User not found with memberId: " + memberId);
        }

        // 권한 설정 (기본적으로 "ROLE_USER" 부여, 필요 시 memberType에 따라 다르게 설정)
        List<GrantedAuthority> authorities = Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_USER")
        );

        // memberType에 따라 권한 부여 (선택적)
        if (member.getMemberType() != null) {
            switch (member.getMemberType()) {
                case "WORKER":
                    authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_WORKER"));
                    break;
                case "COMPANY":
                    authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_COMPANY"));
                    break;
                case "CUSTOMER":
                    authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_CUSTOMER"));
                    break;
                default:
                    authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
            }
        }

        System.out.println("User Loaded - MemberId: " + memberId + ", Authorities: " + authorities);

        return new User(member.getMemberId(), member.getMemberPassword(), authorities);
    }
}