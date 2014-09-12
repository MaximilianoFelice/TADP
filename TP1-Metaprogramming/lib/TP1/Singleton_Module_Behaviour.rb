module TP

  module Singleton_Module_Accessors

    attr_accessor :singleton_module

    def method_missing(method, *args, &block)

      if (singleton_module.method_defined?(method))
        method_to_execute = @singleton_module.instance_method(method)
        method_to_execute.bind(self).call(*args, &block)
      else
        super
      end

    end

    def singleton_module

      if !@singleton_module then
        @singleton_module = Module.new
        self.extend @singleton_module
      end

      @singleton_module

    end

  end

  module Singleton_Module

    include Singleton_Module_Accessors

    # If the module is included by a class, it defines behaviour for every new instance AND for the class instance itself
    def self.included(including_class)
      including_class.singleton_class.include Singleton_Module_Accessors
    end

  end

end