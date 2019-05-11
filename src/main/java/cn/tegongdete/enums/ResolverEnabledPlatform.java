package cn.tegongdete.enums;

import cn.tegongdete.resolver.JueJinListPageResolver;
import cn.tegongdete.resolver.Resolver;

public enum  ResolverEnabledPlatform {

    JUEJIN(BlogPlatform.JUEJIN, JueJinListPageResolver.class);

    private BlogPlatform plateform;
    private Class<?> cla;

    ResolverEnabledPlatform(BlogPlatform platform, Class<?> cla) {
        this.plateform = platform;
        this.cla = cla;
    }

    public BlogPlatform getPlateform() {
        return plateform;
    }

    public Class<?> getCla() {
        return cla;
    }

    public static boolean contains(BlogPlatform platform) {
        for (ResolverEnabledPlatform resolverEnabledPlatform: ResolverEnabledPlatform.values()) {
            if (resolverEnabledPlatform.getPlateform().equals(platform)) {
                return true;
            }
        }
        return false;
    }

    public static Resolver getResolver(BlogPlatform platform) {
        for (ResolverEnabledPlatform resolverEnabledPlatform: ResolverEnabledPlatform.values()) {
            if (resolverEnabledPlatform.getPlateform().equals(platform)) {
                try {
                    return (Resolver) resolverEnabledPlatform.getCla().newInstance();
                }
                catch (Exception e) {
                    return null;
                }
            }
        }
        return null;
    }
}
